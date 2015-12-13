(ns adventofcode.day12-test
  (:require [clojure.test :refer :all]
            [adventofcode.day12 :as day12]))

(deftest test-red-sum
  (testing "red sum"
    (is (= 6 (day12/red-sum [1, 2, 3])))
    (is (= 4 (day12/red-sum [1, {"c" "red" "b" 2}, 3])))
    (is (= 0 (day12/red-sum {"d" "red", "e" [1,2,3,4] "f" 5})))
    (is (= 6 (day12/red-sum [1,"red",5])))))
