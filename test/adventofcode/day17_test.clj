(ns adventofcode.day17-test
  (:require [clojure.test :refer :all]
            [adventofcode.day17 :as day17]))

(deftest test-containers-combinations
  (testing "containers combinations"
    (let [containers [20 15 10 5 5]]
      (is (= 4 (day17/count-container-combinations 25 containers))))))

(deftest test-containers-min-combinations
  (testing "containers min combinations"
    (let [containers [20 15 10 5 5]]
      (is (= 3 (day17/min-container-combinations 25 containers))))))
