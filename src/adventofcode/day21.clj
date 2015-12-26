(ns adventofcode.day21
  (:require [clojure.string :as str]))

(defn p [x] (prn x) x)

(def WEAPONS [
  {:cost 8,  :damage 4, :armor 0}
  {:cost 10, :damage 5, :armor 0}
  {:cost 25, :damage 6, :armor 0}
  {:cost 40, :damage 7, :armor 0}
  {:cost 74, :damage 8, :armor 0}])

(def ARMORS [
  {:cost 13,  :damage 0, :armor 1}
  {:cost 31,  :damage 0, :armor 2}
  {:cost 53,  :damage 0, :armor 3}
  {:cost 75,  :damage 0, :armor 4}
  {:cost 102, :damage 0, :armor 5}])

(def RINGS [
  {:cost 25,  :damage 1, :armor 0}
  {:cost 50,  :damage 2, :armor 0}
  {:cost 100, :damage 3, :armor 0}
  {:cost 20,  :damage 0, :armor 1}
  {:cost 40,  :damage 0, :armor 2}
  {:cost 80,  :damage 0, :armor 3}])

(def NOTHING
  {:cost 0, :damage 0, :armor 0})

(defn round [[{p1-dmg :damage, :as p1} {p2-armor :armor, :as p2}]]
  [(update p2 :hp #(- % (max 1 (- p1-dmg p2-armor)))) p1])

(defn winner [player boss]
  (->> [player boss]
    (iterate round)
    (drop-while (partial every? (comp pos? :hp)))
    (first)
    (filter (comp pos? :hp))
    (first)
    (:name)))

(defn builds []
  (distinct
    (for [weapon WEAPONS
          armor  (cons NOTHING ARMORS)
          ring-1 (cons NOTHING RINGS)
          ring-2 (cons NOTHING RINGS)
          :when (or (not= ring-1 ring-2) (= ring-1 NOTHING) (= ring-2 NOTHING))]
      (merge-with + weapon armor ring-1 ring-2))))

(defn winner-min-gold [boss]
  (->> (builds)
    (map #(assoc % :name :player))
    (map #(assoc % :hp 100))
    (filter #(= :player (winner % boss)))
    (map :cost)
    (apply min)))

(defn loser-max-gold [boss]
  (->> (builds)
    (map #(assoc % :name :player))
    (map #(assoc % :hp 100))
    (filter #(= :boss (winner % boss)))
    (map :cost)
    (apply max)))

(defn main []
  (let [boss {:hp 100, :damage 8, :armor 2, :name :boss}]
    (println "Part 1: " (winner-min-gold boss))
    (println "Part 2: " (loser-max-gold boss))))
