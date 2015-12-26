(ns adventofcode.day21-test
  (:require [clojure.test :refer :all]
            [adventofcode.day21 :as day21]))

(deftest test-round
  (testing "round"
    (let [player {:name "P", :hp 8, :damage 5, :armor 5}
          boss   {:name "B", :hp 12, :damage 7, :armor 20}
          [boss-2 player-2] (day21/round [player boss])]
      (is (= "P" (:name player-2)))
      (is (= "B" (:name boss-2)))
      (is (= 8  (:hp player-2)))
      (is (= 11 (:hp boss-2))))))

(deftest test-winner
  (testing "winner"
    (let [player {:name "P", :hp 8, :damage 5, :armor 5}
          boss   {:name "B", :hp 12, :damage 7, :armor 2}]
      (is (= "P" (day21/winner player boss))))))
