(ns adventofcode.day03-test
  (:require [clojure.test :refer :all]
            [adventofcode.day03 :as day03]))

(deftest test-uniq-houses-1
  (testing "deliver 2 houses"
    (is (= 2 (day03/uniq-houses "^v^v^v^v^v")))))

(deftest test-uniq-houses-2
  (testing "deliver 4 houses"
    (is (= 4 (day03/uniq-houses "^>v<")))))

(deftest test-robo-houses-1
  (testing "deliver 2 houses"
    (is (= 3 (day03/robo-houses "^v")))))

(deftest test-robo-houses-1
  (testing "deliver 11 houses"
    (is (= 11 (day03/robo-houses "^v^v^v^v^v")))))

(deftest test-robo-houses-2
  (testing "deliver 3 houses"
    (is (= 3 (day03/robo-houses "^>v<")))))
