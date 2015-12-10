(ns adventofcode.day10
  (:require [clojure.string :as str]))

(defn iter-look-and-say [seq]
  (->> seq
    (re-seq #"(\d)\1*")
    (map first)
    (map (fn [s] (str (count s) (first s))))
    (apply str)))

(defn look-and-say [times input-seq]
  (loop [count times
         seq input-seq]
    (if (zero? count) seq
      (recur (dec count) (iter-look-and-say seq)))))

(defn main []
  (let [data "1113122113"]
    (println "Sequence size after 40 iters: " (count (look-and-say 40 data)))
    (println "Sequence size after 50 iters: " (count (look-and-say 50 data)))))
