(ns adventofcode.day14
  (:require [clojure.string :as str]))

(defn parse-line [line]
  (let [[match name speed run-time rest-time] (re-find #"\s*(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds." line)]
    {:name name :speed (Integer/parseInt speed) :run-time (Integer/parseInt run-time) :rest-time (Integer/parseInt rest-time)}))

(defn travelled-per-second [{speed :speed run-time :run-time rest-time :rest-time}]
  (reductions +
    (cons 0
      (cycle
        (concat
          (repeat run-time speed)
          (repeat rest-time 0))))))

(defn distance-winner [data goal]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map parse-line)
    (map travelled-per-second)
    (map #(nth % goal))
    (apply max)))

(defn main []
  (let [data (slurp "./inputs/day14.txt")]
    (println "Distance by winner: " (distance-winner data 2503))))
