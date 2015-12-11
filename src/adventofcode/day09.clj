(ns adventofcode.day09
  (:require [clojure.string :as str])
  (:require [clojure.math.combinatorics :as combo]))

(defn dist [distances circuit]
  (->> circuit
    (partition 2 1)
    (map distances)
    (apply +)))

(defn minmax-dist [data]
  (let [distances-map (reduce
                        (fn [ds [from to dist]]
                          (-> ds
                            (assoc (list from to) dist)
                            (assoc (list to from) dist)))
                        {}
                        data)
        circuits-distances  (->> data
                              (map drop-last)
                              (flatten)
                              (distinct)
                              (combo/permutations)
                              (map (partial dist distances-map)))]
        (list (apply min circuits-distances) (apply max circuits-distances))))

(defn parse-line [line]
  (let [[match from to dist] (re-find #"(\w+) to (\w+) = (\d+)" line)]
    (list from to (Integer/parseInt dist))))

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map parse-line)))

(defn main []
  (let [data (slurp "./inputs/day09.txt")]
    (println "Min and max distances: " (minmax-dist (parse-data data)))))
