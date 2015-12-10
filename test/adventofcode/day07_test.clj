(ns adventofcode.day07-test
  (:require [clojure.test :refer :all]
            [adventofcode.day07 :as day07]))

(deftest test-parse-instructions
  (testing "parsing instructions"
    (is (= "x" (:target (day07/parse-instruction "42 -> x"))))
    (is (= 42 (:operation (day07/parse-instruction "42 -> x"))))))

(deftest test-circuit-value
  (testing "solving small example"
    (let [example
"123 -> xx
456 -> yy
xx AND yy -> dd
xx OR yy -> ee
xx LSHIFT 2 -> ff
yy RSHIFT 2 -> gg
xx -> mm
NOT xx -> hh
NOT yy -> ii"]
    (is (= 72 (day07/solve-circuit example "dd")))
    (is (= 507 (day07/solve-circuit example "ee")))
    (is (= 492 (day07/solve-circuit example "ff")))
    (is (= 114 (day07/solve-circuit example "gg")))
    (is (= 65412 (day07/solve-circuit example "hh")))
    (is (= 65079 (day07/solve-circuit example "ii")))
    (is (= 123 (day07/solve-circuit example "xx")))
    (is (= 123 (day07/solve-circuit example "mm")))
    (is (= 456 (day07/solve-circuit example "yy"))))))
