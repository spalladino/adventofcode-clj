(ns adventofcode.day11-test
  (:require [clojure.test :refer :all]
            [adventofcode.day11 :as day11]))

(deftest test-inc-passwd
  (testing "inc passwd"
    (is (= "abce" (day11/inc-passwd "abcd")))
    (is (= "abda" (day11/inc-passwd "abcz")))
    (is (= "acaa" (day11/inc-passwd "abzz")))
    (is (= "baaa" (day11/inc-passwd "azzz")))))

(deftest test-valid-passwd
  (testing "valid passwd"
    (is (day11/letter-streak "hijklmmn"))
    (is (not (day11/no-confusing "hijklmmn")))
    (is (day11/two-pairs "abbceffg"))
    (is (not (day11/letter-streak "abbceffg")))
    (is (not (day11/two-pairs "abbcegjk")))))

(deftest test-next-passwd
  (testing "next passwd"
    (is (= "abcdffaa" (day11/next-passwd "abcdefgh")))
    (is (= "ghjaabcc" (day11/next-passwd "ghijklmn")))))
