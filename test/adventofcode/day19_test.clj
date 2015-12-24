(ns adventofcode.day19-test
  (:require [clojure.test :refer :all]
            [adventofcode.day19 :as day19]))

(deftest test-calibration
  (testing "calibration"
    (let [data "
H => HO
H => OH
O => HH

HOH"]
      (is (= 4 (day19/calibration (day19/parse-data data)))))))
