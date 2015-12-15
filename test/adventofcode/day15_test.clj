(ns adventofcode.day15-test
  (:require [clojure.test :refer :all]
            [adventofcode.day15 :as day15]))

(deftest test-highest-score
  (testing "cookie with highest score"
    (let [data "
    Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
    Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
    "]
      (is (= 62842880 (day15/highest-score data))))))

(deftest test-highest-score-with-max-cals
  (testing "cookie with highest score with max cals"
    (let [data "
    Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
    Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
    "]
      (is (= 57600000 (day15/highest-score-with-cals data))))))

(deftest test-score
  (testing "score of cookie"
    (let [ingredients '({"capacity" -1, "durability" -2, "flavor" 6, "texture" 3, "calories" 8} {"capacity" 2, "durability" 3, "flavor" -2, "texture" -1, "calories" 3})
          amounts [44 56]]
      (is (= 62842880 (day15/score ingredients amounts))))))
