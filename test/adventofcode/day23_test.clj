(ns adventofcode.day23-test
  (:require [clojure.test :refer :all]
            [adventofcode.day23 :as day23]))

(deftest test-run
  (testing "simple execution"
    (let [instructions [`(day23/instr-inc :a) `(day23/instr-jio :a 2) `(day23/instr-tpl :a) `(day23/instr-inc :a)]]
      (is (= 2 (:a (day23/run instructions { :pc 0, :a 0, :b 0 })))))))
