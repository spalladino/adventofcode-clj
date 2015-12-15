(ns adventofcode.day14-test
  (:require [clojure.test :refer :all]
            [adventofcode.day14 :as day14]))

(deftest test-distance-winner
  (testing "winner by distance"
    (let [data "
    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
    Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
    "]
      (is (= 1120 (day14/distance-winner data 1000))))))

(deftest test-score-winner
  (testing "winner by score"
    (let [data "
    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
    Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
    "]
      (is (= 689 (day14/score-winner data 1000))))))
