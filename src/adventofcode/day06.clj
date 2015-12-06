(ns adventofcode.day06
  (:require [clojure.string :as str])
  (:require [clojure.math.combinatorics :as combo]))

(defn apply-light [action lights pos]
  (case action
    :turn-on (assoc lights pos true)
    :turn-off (assoc lights pos false)
    :toggle (update lights pos not)))

(defn apply-instruction [lights instruction]
  (let [[action from-i from-j to-i to-j] instruction]
    (reduce
      (partial apply-light action) lights
      (combo/cartesian-product
        (range from-i (inc to-i))
        (range from-j (inc to-j))))))

(defn lights-on [instructions]
  (->> instructions
    (reduce apply-instruction {})
    (vals)
    (filter identity)
    (count)))

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
    (println "Number of lights on: " (lights-on (parse data)))))
