(ns adventofcode.day13
  (:require [clojure.string :as str])
  (:require [clojure.math.combinatorics :as combo]))

(defn collect-lines [lines]
  (reduce (fn [data line]
            (let [[match who action amount-str whom] (re-find #"(\w+) would (lose|gain) (\d+) happiness units by sitting next to (\w+)" line)
                  amount (Integer/parseInt amount-str)
                  value (if (= action "lose") (- amount) amount)]
              (assoc data (list who whom) value)))
          {} lines))

(defn happiness-val [values seating]
  (let [pairs (->> seating
                (cycle)
                (partition 2 1)
                (take (count seating)))]
    (apply +
      (map #(or (values %) 0)
        (concat pairs (map reverse pairs))))))

(defn happiness [data include-self]
  (let [happiness-values (->> data
                          (str/split-lines)
                          (remove str/blank?)
                          (collect-lines))]
    (->> happiness-values
      (keys)
      (flatten)
      (distinct)
      ((if include-self (partial cons :self) identity))
      (combo/permutations)
      (map (partial happiness-val happiness-values))
      (apply max))))

(defn main []
  (let [data (slurp "./inputs/day13.txt")]
    (println "Happiness:           " (happiness data false))
    (println "Happiness with self: " (happiness data true))))
