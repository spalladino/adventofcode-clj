(ns adventofcode.day01-test
  (:require [clojure.test :refer :all]
            [adventofcode.day01 :as day01]))

(deftest test-floor
  (testing "count to 3rd floor"
    (is (= 3 (day01/floor ")(((()(")))))

(deftest test-basement
  (testing "basement at position 5"
    (is (= 5 (day01/basement "()()))((((()))))))))")))))
