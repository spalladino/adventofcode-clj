(ns adventofcode.day22-test
  (:require [clojure.test :refer :all]
            [adventofcode.day22 :as day22]))

(deftest test-battle-1
  (testing "first battle"
    (let [player  {:hp 10, :mana 250, :armor 0}
          boss    {:hp 13, :damage 8, :armor 0}
          effects day22/INITIAL-EFFECTS
          match   day22/INITIAL-MATCH
          state-0 [effects player boss match]
          [effects-1 player-1 boss-1 match-1 :as state-1] (day22/round state-0 :poison)
          [effects-2 player-2 boss-2 match-2 :as state-2] (day22/round state-1 :magic-missile)]

      (is (= {:hp 2, :armor 0, :mana 77} player-1))
      (is (= 10 (:hp boss-1)))
      (is (= 5 (:poison effects-1)))
      (is (= nil (:winner match-1)))

      (is (= {:hp 2, :armor 0, :mana 24} player-2))
      (is (= 0 (:hp boss-2)))
      (is (= 3 (:poison effects-2)))
      (is (= :player (:winner match-2)))
      (is (= (+ 173 53) (:mana-spent match-2))))))

(deftest test-battle-2
  (testing "second battle"
    (let [player  {:hp 10, :mana 250, :armor 0}
          boss    {:hp 14, :damage 8, :armor 0}
          effects day22/INITIAL-EFFECTS
          match   day22/INITIAL-MATCH
          state-0 [effects player boss match]
          state-1 (day22/round state-0 :recharge)
          state-2 (day22/round state-1 :shield)
          state-3 (day22/round state-2 :drain)
          state-4 (day22/round state-3 :poison)
          [effects-5 player-5 boss-5 match-5 :as state-5] (day22/round state-4 :magic-missile)]

      (is (= {:hp 1, :armor 0, :mana 114} player-5))
      (is (= -1 (:hp boss-5)))
      (is (= :player (:winner match-5)))
      (is (= (apply + (map day22/COST [:recharge :shield :drain :poison :magic-missile])) (:mana-spent match-5))))))

(deftest test-valid-action
  (testing "valid action"
    (let [player  {:mana 200}
          effects (assoc day22/INITIAL-EFFECTS :shield 2)
          state   [effects player {} {}]]

      (is (day22/valid-action? state :magic-missile))
      (is (day22/valid-action? state :drain))
      (is (not (day22/valid-action? state :shield)))
      (is (day22/valid-action? state :poison))
      (is (not (day22/valid-action? state :recharge))))))
