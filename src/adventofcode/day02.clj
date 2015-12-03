(ns adventofcode.day02
  (:require [clojure.string :as str]))

(defn paper [size]
  (let [[l w h] size
        faces [(* l w) (* w h) (* h l)]]
    (+
      (* 2 (reduce + faces))
      (apply min faces))))

(defn ribbon [size]
  (let [[l w h] size
        volume (* l w h)]
    (+
      (* 2 (- (+ l w h) (max l w h)))
      volume)))

(defn total-paper [sizes]
  (reduce + (map paper sizes)))

(defn total-ribbon [sizes]
  (reduce + (map ribbon sizes)))

(defn main []
  (let [data (slurp "./inputs/day02.txt")
        lines (str/split data #"\n")
        sizes (map (fn [line] (map #(Integer/parseInt %) (str/split line #"x"))) lines)]
    (println "Total paper required:  " (total-paper sizes))
    (println "Total ribbon required: " (total-ribbon sizes))))
