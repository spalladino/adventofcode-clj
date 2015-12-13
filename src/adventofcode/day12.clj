(ns adventofcode.day12
  (:require [clojure.string :as str])
  (:require [clojure.data.json :as json]))

(defn json-sum [data]
  (apply +
    (map #(Integer/parseInt %)
      (re-seq #"-?\d+" data))))

(defmulti red-sum class)

(defmethod red-sum java.lang.String [str]
  0)

(defmethod red-sum java.lang.Number [num]
  num)

(defmethod red-sum java.util.Collection [array]
  (apply + (map red-sum array)))

(defmethod red-sum java.util.Map [obj]
  (if (.contains (vals obj) "red") 0
    (+
      (red-sum (vals obj))
      (red-sum (keys obj)))))

(defn main []
  (let [data (slurp "./inputs/day12.json")]
    (println "Numbers sum:     " (json-sum data))
    (println "Numbers red sum: " (red-sum (json/read-str data)))))
