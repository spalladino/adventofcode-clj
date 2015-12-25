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

(deftest test-generation-bfs
    (testing "generation bfs"
      (let [data "
e => H
e => O
H => HO
H => OH
O => HH

HOH"]
        (is (= 3 (day19/generation-bfs (day19/parse-data data)))))))

(deftest test-generation-bfs-2
      (testing "generation bfs favourite molecule"
        (let [data "
e => H
e => O
H => HO
H => OH
O => HH

HOHOHO"]
          (is (= 6 (day19/generation-bfs (day19/parse-data data)))))))

(deftest test-generation-heur
    (testing "generation heur"
      (let [data "
e => H
e => O
H => HO
H => OH
O => HH

HOH"]
        (is (= '("e" 3) (day19/generation-heur (day19/parse-data data)))))))

(deftest test-generation-heur-2
      (testing "generation heur favourite molecule"
        (let [data "
e => H
e => O
H => HO
H => OH
O => HH

HOHOHO"]
          (is (= '("e" 6) (day19/generation-heur (day19/parse-data data)))))))

(deftest test-generation-dfs
    (testing "generation dfs"
      (let [data "
e => H
e => O
H => HO
H => OH
O => HH

HOH"]
        (is (= 3 (day19/generation-dfs (day19/parse-data data)))))))

(deftest test-generation-dfs-2
      (testing "generation dfs favourite molecule"
        (let [data "
e => H
e => O
H => HO
H => OH
O => HH

HOHOHO"]
          (is (= 6 (day19/generation-dfs (day19/parse-data data)))))))
