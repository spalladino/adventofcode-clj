(ns adventofcode.day17
  (:require [clojure.string :as str]))

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map #(Integer/parseInt %))))

(defn extend-solution [[num target :as partial] container]
  (if (> container target)
    [partial]
    [partial (list (inc num) (- target container))]))

(defn container-combinations [target containers]
  (let [extend-solutions (fn [partials container] (mapcat #(extend-solution %1 container) partials))]
    (->> containers
      (reduce extend-solutions [(list 0 target)])
      (filter (comp zero? second)))))

(defn count-container-combinations [target containers]
  (count (container-combinations target containers)))

(defn min-container-combinations [target containers]
  (let [counts (map first (container-combinations target containers))
        min-count (apply min counts)]
    (count
      (filter #(= min-count %) counts))))

; (defn container-min-combinations [target containers]
;   (let [extend-solutions (fn [partials container] (mapcat #(extend-solution %1 container) partials))]
;     (->> containers
;       (reduce extend-solutions [target])
;       (filter zero?)
;       (count))))


(defn main []
  (let [containers (parse-data (slurp "./inputs/day17.txt"))]
    (println "Container combinations: " (count-container-combinations 150 containers))
    (println "Minimum combinations:   " (min-container-combinations 150 containers))))
