(ns adventofcode.day20
  (:require [clojure.string :as str]))

; Does not work for values under ~2500
(defn house-presents-upto-50 [num]
  (->>
    (range 1 51)
    (filter #(zero? (mod num %)))
    (reduce #(+ %1 (/ num %2)) 0)
    (* 11)))

(defn house-presents [num]
  (let [sqrt (long (Math/sqrt num))
        sum (->>
              (range 1 (inc sqrt))
              (filter #(zero? (mod num %)))
              (reduce #(+ %1 %2 (/ num %2)) 0))]
    (* 10
      (if (= (* sqrt sqrt) num)
        (- sum sqrt)
        sum))))

(defn first-house [presents-fn goal]
  (->> (range)
    (map presents-fn)
    (take-while #(< % goal))
    (count)))

(defn main []
  (let [data 36000000]
    (println "Part 1: " (first-house house-presents data))
    (println "Part 2: " (first-house house-presents-upto-50 data))))
