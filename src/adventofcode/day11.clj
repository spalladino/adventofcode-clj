(ns adventofcode.day11
  (:require [clojure.string :as str]))

(defn inc-passwd [passwd]
  (apply str
    (reverse
      (loop [[head & tail] (reverse passwd)
             value []]
        (if (= head \z)
          (recur tail (conj value \a))
          (concat
            (conj value (char (inc (int head))))
            tail))))))

(defn letter-streak [passwd]
  (let [groups  (map
                  (partial apply str)
                  (partition 3 1
                    (map char (range 97 123))))]
    (some
      #(.contains passwd %)
      groups)))

(defn no-confusing [passwd]
  (not (re-find #"i|o|l" passwd)))

(defn two-pairs [passwd]
  (< 1 (count (re-seq #"(\w)\1" passwd))))

(defn valid-passwd [passwd]
  (and
    (no-confusing passwd)
    (letter-streak passwd)
    (two-pairs passwd)))

(defn next-passwd [passwd]
  (->> passwd
    (inc-passwd)
    (iterate inc-passwd)
    (filter valid-passwd)
    (first)))

(defn main []
  (let [data "hxbxwxba"]
    (println "Next pass:  " (next-passwd data))
    (println "After that: " (next-passwd (next-passwd data)))))
