(ns adventofcode.core
  (:require [adventofcode.day01 :as day01])
  (:require [adventofcode.day02 :as day02])
  (:require [adventofcode.day03 :as day03]))

(defn -main []
  (println "Day 01")
  (day01/main)
  (println "Day 02")
  (day02/main)
  (println "Day 03")
  (day03/main))
