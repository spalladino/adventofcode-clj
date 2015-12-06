(ns adventofcode.day06
  (:require [clojure.string :as str])
  (:require [clojure.math.combinatorics :as combo]))

(defn apply-brightness [action lights pos]
  (case action
    :turn-off (update lights pos #(max 0 ((fnil dec 0) %)))
    :turn-on (update lights pos (fnil inc 0))
    :toggle (update lights pos (fnil (comp inc inc) 0))))

(defn apply-light [action lights pos]
  (case action
    :turn-on (assoc lights pos true)
    :turn-off (assoc lights pos false)
    :toggle (update lights pos not)))

(defn apply-instruction [apply-func lights instruction]
  (let [[action from-i from-j to-i to-j] instruction]
    (reduce
      (partial apply-func action) lights
      (combo/cartesian-product
        (range from-i (inc to-i))
        (range from-j (inc to-j))))))

(defn lights-on [instructions]
  (->> instructions
    (reduce (partial apply-instruction apply-light) {})
    (vals)
    (filter identity)
    (count)))

(defn lights-brightness [instructions]
  (->> instructions
    (reduce (partial apply-instruction apply-brightness) {})
    (vals)
    (apply +)))

(defn parse [data]
  (->> data
    (str/split-lines)
    (remove empty?)
    (map
      (fn [line]
        (let [[match action from-i from-j to-i to-j] (re-find #"(turn on|turn off|toggle) (\d+),(\d+) through (\d+),(\d+)" line)]
          [({ "turn on" :turn-on "turn off" :turn-off "toggle" :toggle } action)
            (Integer/parseInt from-i)
            (Integer/parseInt from-j)
            (Integer/parseInt to-i)
            (Integer/parseInt to-j)])))))

(defn main []
  (let [data (slurp "./inputs/day06.txt")]
    (println "Number of lights on:  " (lights-on (parse data)))
    (println "Brightness of lights: " (lights-brightness (parse data)))))
