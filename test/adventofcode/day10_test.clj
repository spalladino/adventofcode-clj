(ns adventofcode.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode.day10 :as day10]))

(deftest test-iter-look-and-say
  (testing "iter look-and-say"
    (is (= "312211" (day10/iter-look-and-say "111221")))))

(deftest test-look-and-say
  (testing "look-and-say"
    (is (= "312211" (day10/look-and-say 5 "1")))))
