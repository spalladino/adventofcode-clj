(ns adventofcode.day25
  (:require [clojure.string :as str]))

(def START 20151125)

(defn next-code [code]
  (mod (* code 252533) 33554393))

(defn code-index [index]
  (nth (iterate next-code START) (dec index)))

(defn index-at [row col]
  (let [row-base (inc (reduce + (range row)))
        col-offset (reduce + (range (inc row) (+ row col)))]
    (+ row-base col-offset)))

(defn code-at [row col]
  (code-index (index-at row col)))

(defn main []
  (let [row 2978
        col 3083]
    (println "Part 1: " (code-at row col))))
