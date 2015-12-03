(ns adventofcode.day02
  (:require [clojure.string :as str]))

(defn paper [size]
  (let [[l w h] size
        faces [(* l w) (* w h) (* h l)]]
    (+
      (* 2 (reduce + faces))
      (apply min faces))))

(defn total-paper [sizes]
  (reduce + (map paper sizes)))

(defn main []
  (let [data (slurp "./inputs/day02.txt")
        lines (str/split data #"\n")
        sizes (map (fn [line] (map #(Integer/parseInt %) (str/split line #"x"))) lines)]
    (println "Total paper required: " (total-paper sizes))))
