(ns adventofcode.core
  (:require [adventofcode.day01 :as day01])
  (:require [adventofcode.day02 :as day02]))

(defn -main []
  (println "Day 01")
  (day01/main)
  (println "Day 02")
  (day02/main))
