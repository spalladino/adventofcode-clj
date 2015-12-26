(ns adventofcode.core)

(def DAYS 20)

(doall
  (map (fn [day] (eval `(require '[~(symbol (format "adventofcode.day%02d" (inc day))) :as ~(symbol (format "day%02d" (inc day)))])))
  (range DAYS)))

(def days
  (apply hash-map
    (flatten
      (map
        (fn [day] [(inc day) (eval (symbol (format "day%02d/main" (inc day))))])
        (range DAYS)))))

(defn -main [arg]
  (let [day (Integer/parseInt arg)]
    (println (str "Day " day))
    ((days day))))
