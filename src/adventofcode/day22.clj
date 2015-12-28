(ns adventofcode.day22
  (:require [clojure.string :as str]))

(def RECHARGE-MANA 101)
(def SHIELD-BONUS 7)
(def POISON-DAMAGE 3)
(def DRAIN-HP 2)
(def MISSILE-DAMAGE 4)

(def RECHARGE-DURATION 5)
(def SHIELD-DURATION 6)
(def POISON-DURATION 6)

(def MAX-ROUNDS 12)

(def ACTIONS [
  :magic-missile
  :drain
  :shield
  :poison
  :recharge])

(def COST
  { :magic-missile 53
    :drain 73
    :shield 113
    :poison 173
    :recharge 229 })

(def INITIAL-EFFECTS
  { :shield 0
    :poison 0
    :recharge 0 })

(def INITIAL-MATCH
  { :winner nil
    :mana-spent 0
    :spells-cast [] })

(defn round-winner? [player boss]
  (if (not (pos? (:hp player))) :boss
    (if (not (pos? (:hp boss))) :player
      nil)))

(defn round-effects [[{shield-turns :shield, poison-turns :poison, recharge-turns :recharge, :as effects} player boss match, :as state]]
  (if (:winner match) state
    (let [new-effects (reduce (fn [e k] (update e k #(max 0 (dec %)))) effects [:shield :poison :recharge])
          new-player (-> player
                        (assoc :armor (if (pos? shield-turns) SHIELD-BONUS 0))
                        (update :mana (if (pos? recharge-turns) (partial + RECHARGE-MANA) identity)))
          new-boss (-> boss
                      (update :hp (if (pos? poison-turns) #(- % POISON-DAMAGE) identity)))
          new-match (if-let [winner (round-winner? new-player new-boss)]
                      (assoc match :winner winner)
                      match)]
      [new-effects new-player new-boss new-match])))

(defn round-player [[effects player boss match, :as state] action]
  (if (:winner match) state
    (let [new-effects (case action
                         :shield (assoc effects :shield SHIELD-DURATION)
                         :poison (assoc effects :poison POISON-DURATION)
                         :recharge (assoc effects :recharge RECHARGE-DURATION)
                         effects)
          new-player (update
                        (case action
                          :drain (update player :hp (partial + DRAIN-HP))
                          player)
                        :mana
                        #(- % (COST action)))
          new-boss (case action
                        :drain (update boss :hp #(- % DRAIN-HP))
                        :magic-missile (update boss :hp #(- % MISSILE-DAMAGE))
                        boss)
          new-match (-> (if-let [winner (round-winner? new-player new-boss)]
                          (assoc match :winner winner)
                          match)
                      (update :mana-spent (partial + (COST action)))
                      (update :spells-cast conj action))]
      [new-effects new-player new-boss new-match])))

(defn round-boss [[effects {player-armor :armor, :as player} {boss-damage :damage, :as boss} match, :as state]]
  (if (:winner match) state
    (let [new-player (update player :hp #(- % (max 1 (- boss-damage player-armor))))
          new-match (if-let [winner (round-winner? new-player boss)]
                      (assoc match :winner winner)
                      match)]
      [effects new-player boss new-match])))

(defn round [[effects player boss match :as state] action]
  (-> state
    (round-effects)
    (round-player action)
    (round-effects)
    (round-boss)))

(defn valid-action? [[effects player boss match, :as state] action]
  (and
    (or (nil? (effects action)) (>= 1 (effects action)))
    (>= (player :mana) (COST action))))

(defn min-match [states]
  (let [won-matches (filter #(= :player (% :winner)) (map last states))]
    (if (empty? won-matches) nil
      (apply min-key :mana-spent won-matches))))

(defn extend-battle [[effects player boss match, :as state]]
  (if (not (nil? (match :winner)))
    [state]
    (->> ACTIONS
      (filter (partial valid-action? state))
      (map (partial round state)))))

(defn extend-battles [states]
  (let [extended (mapcat extend-battle states)]
    (if-let [min-solution (:mana-spent (min-match extended))]
      (remove #(> (:mana-spent (last %)) min-solution) extended)
      extended)))

(defn winner-min-mana [player boss]
  (let [initial-state [INITIAL-EFFECTS player boss INITIAL-MATCH]
        battles (nth (iterate extend-battles [initial-state]) MAX-ROUNDS)]
    (min-match battles)))

(defn main []
  (let [player {:hp 50, :mana 500, :armor 0, :name :player}
        boss {:hp 55, :damage 8, :armor 0, :name :boss}]
    (println "Least mana for winning: " (winner-min-mana player boss))))
