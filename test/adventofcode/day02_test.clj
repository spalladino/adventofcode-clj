(ns adventofcode.day02-test
  (:require [clojure.test :refer :all]
            [adventofcode.day02 :as day02]))

(deftest test-paper
  (testing "wrapping two presents"
    (is (=
      (+ 58 43)
      (day02/total-paper [[2,3,4], [1,1,10]])))))

(deftest test-ribbon
  (testing "ribbon for two presents"
    (is (=
      (+ 34 14)
      (day02/total-ribbon [[2,3,4], [1,1,10]])))))
