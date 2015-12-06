(ns adventofcode.day06-test
  (:require [clojure.test :refer :all]
            [adventofcode.day06 :as day06]))

(deftest test-instructions
  (testing "some instructions"
    (is (= 1003 (day06/lights-on [
      [:turn-on 499 499 500 500]
      [:toggle 0 0 999 0]
      [:turn-off 0 0 0 0]
      ])))))
