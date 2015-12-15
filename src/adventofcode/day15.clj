(ns adventofcode.day15
  (:require [clojure.string :as str]))

(def ^:dynamic max-cals nil)

(def score-keys ["capacity" "durability" "flavor" "texture"])

(defn parse-line [line]
  (let [scores (re-seq #"(\w+) (-?\d+)" line)]
    (into {}
      (map
        (fn [[_ name score]]
          [name (Integer/parseInt score)])
        scores))))

(defn parse-ingredients [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map parse-line)))

(defn extend-solution [qtys]
  (let [current-total (apply + qtys)]
    (map
      #(conj qtys %)
      (range (- 101 current-total)))))

(defn complete-solution [qtys]
  (let [current-total (apply + qtys)]
    (conj qtys (- 100 current-total))))

(defn valid-solution? [ingredients solution]
  (if-not max-cals true
    (let [calories (map #(% "calories") ingredients)]
      (= max-cals
        (apply +
          (map * calories solution))))))

(defn solutions [ingredients]
  (filter
    (partial valid-solution? ingredients)
    (map complete-solution
      (nth
        (iterate (partial mapcat extend-solution) [[]])
        (dec (count ingredients))))))

(defn properties-score [ingredient qty]
  (vec (map #(* qty (ingredient %)) score-keys)))

(defn score [ingredients solution]
  (->> solution
    (map properties-score ingredients)
    (apply map +)
    (map (partial max 0))
    (apply *)))

(defn highest-score [data]
  (let [ingredients (parse-ingredients data)
        combinations (solutions ingredients)]
    (->> combinations
      (map (partial score ingredients))
      (apply max))))

(defn highest-score-with-cals [data]
  (binding [max-cals 500]
    (highest-score data)))

(defn main []
  (let [data (slurp "./inputs/day15.txt")]
    (println "Highest scored cookie: " (highest-score data))
    (println "With calories limit:   " (highest-score-with-cals data))))
