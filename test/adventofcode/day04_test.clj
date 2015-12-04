(ns adventofcode.day04-test
  (:require [clojure.test :refer :all]
            [adventofcode.day04 :as day04]))

(deftest test-hash-input-zeros-1
  (testing "returns 609043"
    (is (= 609043 (day04/hash-input-match "abcdef" "00000")))))

(deftest test-hash-input-zeros-2
  (testing "returns 1048970"
    (is (= 1048970 (day04/hash-input-match "pqrstuv" "00000")))))
