(ns adventofcode.day18
  (:require [clojure.string :as str])
  (:require [clojure.math.combinatorics :as combo]))

(def ^:dynamic *corners-on* false)

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map (fn [row] (vec (map #(case % \# :on \. :off) row))))))

(defn grid-at [grid i j]
  (let [m (count grid)
        n (count (first grid))]
    (if (and *corners-on* (or
          (and (= i 0) (= j 0))
          (and (= i 0) (= j (dec n)))
          (and (= i (dec m)) (= j 0))
          (and (= i (dec m)) (= j (dec n)))))
        :on
        (try
          (-> grid
            (nth i)
            (nth j))
          (catch java.lang.IndexOutOfBoundsException _ :off)))))

(defn grid-neighbours [grid i j]
  (for [di [-1 0 1] dj [-1 0 1] :when (not= [0 0] [di dj])]
    (grid-at grid (+ i di) (+ j dj))))

(defn count-grid-neighbours [grid i j target]
  (count (filter #(= target %) (grid-neighbours grid i j))))

(defn next-status [grid i j]
  (let [on-count (count-grid-neighbours grid i j :on)]
    (case (grid-at grid i j)
      :on  (if (or (= 2 on-count) (= 3 on-count)) :on :off)
      :off (if (= 3 on-count) :on :off))))

(defn conway-next [grid]
  (let [m (count grid)
        n (count (first grid))]
    (map (fn [i]
      (map (fn [j]
        (next-status grid i j))
        (range m)))
      (range n))))

(defn conway-count-on [initial steps]
  (let [m (count initial)
        n (count (first initial))
        grid (nth (iterate conway-next initial) steps)]
  (count
    (filter
      #(= :on %)
      (for [i (range m) j (range n)] (grid-at grid i j))))))

(defn main []
  (let [data (parse-data (slurp "./inputs/day18.txt"))]
    (println "Lights on:       " (conway-count-on data 100))
    (println "With corners on: " (binding [*corners-on* true] (conway-count-on data 100)))))
