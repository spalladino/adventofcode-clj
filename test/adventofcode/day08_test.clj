(ns adventofcode.day08-test
  (:require [clojure.test :refer :all]
            [adventofcode.day08 :as day08]))

(deftest test-chars-diff
  (testing "chars diff"
    (let [example "
\"\"
\"abc\"
\"aaa\\\"aaa\"
\"\\x27\"
"]
    (is (= 12 (day08/chars-diff day08/diff-mem example)))
    (is (= 19 (day08/chars-diff day08/diff-enc example))))))
