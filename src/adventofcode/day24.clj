(ns adventofcode.day24
  (:require [clojure.string :as str])
  (:require [clojure.math.combinatorics :as combo]))

(defn p [x] (prn x) x)

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map #(Integer/parseInt %))))

(defn min-keys [fun & values]
  (let [min-val (apply min (map fun values))]
    (filter #(= (fun %) min-val) values)))

(defn best-arrangement [packages]
  (let [target-weight (/ (apply + packages) 3)
        partitions (combo/partitions packages :min 3 :max 3)]
    (->> partitions
      (filter (partial every? #(= (apply + %) target-weight)))
      (apply concat)
      (apply min-keys count)
      (map (partial apply *))
      (apply min))))

(defn main []
  (let [packages (parse-data (slurp "./inputs/day24.txt"))]
    (println "Part 1: " (best-arrangement packages))))
