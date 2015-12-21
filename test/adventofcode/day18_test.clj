(ns adventofcode.day18-test
  (:require [clojure.test :refer :all]
            [adventofcode.day18 :as day18]))

(deftest test-conway
  (testing "conway"
    (let [data "
.#.#.#
...##.
#....#
..#...
#.#..#
####..
"
         initial (day18/parse-data data)]
      (is (= 4 (day18/conway-count-on initial 4)))
      (is (= 17 (binding [day18/*corners-on* true] (day18/conway-count-on initial 5)))))))
