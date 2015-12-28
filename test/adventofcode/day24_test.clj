(ns adventofcode.day24-test
  (:require [clojure.test :refer :all]
            [adventofcode.day24 :as day24]))

(deftest test-best-arrangement
  (testing "best arrangement for ten packages"
    (let [packages [1 2 3 4 5 7 8 9 10 11]]
      (is (= 99 (day24/best-arrangement packages))))))
