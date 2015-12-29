(ns adventofcode.day24
  (:require [clojure.string :as str])
  (:require [clojure.set :as cset])
  (:require [clojure.math.combinatorics :as combo]))

(def ^:dynamic *group-count* 3)

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map #(Integer/parseInt %))))

(defn min-keys [fun & values]
  (let [min-val (apply min (map fun values))]
    (filter #(= (fun %) min-val) values)))

(defn can-split? [packages target]
  (some
    #(= target (apply + (first %)))
    (combo/partitions packages :min (dec *group-count*) :max (dec *group-count*))))

(defn first-groups-sized [packages size]
  (let [target-weight (/ (apply + packages) *group-count*)]
    (->> size
      (combo/combinations packages)
      (filter #(= target-weight (apply + %))))))

(defn first-groups [packages]
  (->> (range)
    (map (partial first-groups-sized packages))
    (filter not-empty)
    (first)))

(defn best-arrangement [packages]
  (let [target-weight (/ (apply + packages) *group-count*)]
    (->> packages
      (first-groups)
      (filter #(can-split? (vec (cset/difference (set packages) (set %))) target-weight))
      (map (partial apply *))
      (apply min))))

(defn main []
  (let [packages (parse-data (slurp "./inputs/day24.txt"))]
    (println "Groups of 3: " (best-arrangement packages))
    (println "Groups of 4: " (binding [*group-count* 4] (best-arrangement packages)))))
