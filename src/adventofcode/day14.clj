(ns adventofcode.day14
  (:require [clojure.string :as str]))

(defn parse-line [line]
  (let [[match name speed run-time rest-time] (re-find #"\s*(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds." line)]
    {:name name :speed (Integer/parseInt speed) :run-time (Integer/parseInt run-time) :rest-time (Integer/parseInt rest-time)}))

(defn travelled-per-second [{speed :speed run-time :run-time rest-time :rest-time}]
  (reductions +
    (cycle
      (concat
        (repeat run-time speed)
        (repeat rest-time 0)))))

(defn distances-travelled [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map parse-line)
    (map travelled-per-second)))

(defn score-winner [data goal]
  (let [distances-per-second (distances-travelled data)
        max-per-second (apply map max distances-per-second)
        is-winner-per-second (map (partial map = max-per-second) distances-per-second)
        scores-per-second (map (fn [scores] (map #(if % 1 0) scores)) is-winner-per-second)
        accums-per-second (map (partial reductions +) scores-per-second)]
    (apply max
      (map #(nth % goal) accums-per-second))))

(defn distance-winner [data goal]
  (->> data
    (distances-travelled)
    (map #(nth % (dec goal)))
    (apply max)))

(defn main []
  (let [data (slurp "./inputs/day14.txt")]
    (println "Winner distance: " (distance-winner data 2503))
    (println "Winner score:    " (score-winner data 2503))))
