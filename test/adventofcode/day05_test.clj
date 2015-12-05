(ns adventofcode.day05-test
  (:require [clojure.test :refer :all]
            [adventofcode.day05 :as day05]))

(deftest test-nice-string-1
  (testing "ugknbfddgicrmopn is nice"
    (is (day05/is-nice-string "ugknbfddgicrmopn"))))

(deftest test-nice-string-2
  (testing "aaa is nice"
    (is (day05/is-nice-string "aaa"))))

(deftest test-nice-string-3
  (testing "jchzalrnumimnmhp is not nice"
    (is (not (day05/is-nice-string "jchzalrnumimnmhp")))))

(deftest test-nice-string-4
  (testing "haegwjzuvuyypxyu is not nice"
    (is (not (day05/is-nice-string "haegwjzuvuyypxyu")))))

(deftest test-nice-string-5
  (testing "dvszwmarrgswjxmb is not nice"
    (is (not (day05/is-nice-string "dvszwmarrgswjxmb")))))

(deftest test-nice-string-6
  (testing "dvszwmarrgswjxmb is not nice"
    (is (not (day05/is-nice-string "xdwduffwgcptfwad")))))

(deftest test-nice-string-p2-1
  (testing "qjhvhtzxzqqjkmpb is nice"
    (is (day05/is-nice-string-v2 "qjhvhtzxzqqjkmpb"))))

(deftest test-nice-string-p2-2
  (testing "xxyxx is nice"
    (is (day05/is-nice-string-v2 "xxyxx"))))

(deftest test-nice-string-p2-3
  (testing "uurcxstgmygtbstg is not nice"
    (is (not (day05/is-nice-string-v2 "uurcxstgmygtbstg")))))

(deftest test-nice-string-p2-4
  (testing "ieodomkazucvgmuy is not nice"
    (is (not (day05/is-nice-string-v2 "ieodomkazucvgmuy")))))

(deftest test-nice-string-p2-5
  (testing "aaa is not nice"
    (is (not (day05/is-nice-string-v2 "aaa")))))
