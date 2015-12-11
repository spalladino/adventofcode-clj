(ns adventofcode.day09-test
  (:require [clojure.test :refer :all]
            [adventofcode.day09 :as day09]))

(deftest test-shortest-dist
  (testing "shortest distance"
    (let [example "
London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141
"]
    (is (= (list 605 982) (day09/minmax-dist (day09/parse-data example)))))))
