(ns adventofcode.day20-test
  (:require [clojure.test :refer :all]
            [adventofcode.day20 :as day20]))

(deftest test-house-presents
  (testing "house presents"
    (is (= 10 (day20/house-presents 1)))
    (is (= 30 (day20/house-presents 2)))
    (is (= 40 (day20/house-presents 3)))
    (is (= 70 (day20/house-presents 4)))
    (is (= 60 (day20/house-presents 5)))
    (is (= 120 (day20/house-presents 6)))
    (is (= 80 (day20/house-presents 7)))
    (is (= 150 (day20/house-presents 8)))
    (is (= 130 (day20/house-presents 9)))))

(deftest test-first-house-presents
  (testing "first house to receive presents"
    (is (= 4 (day20/first-house day20/house-presents 65)))
    (is (= 8 (day20/first-house day20/house-presents 130)))))
