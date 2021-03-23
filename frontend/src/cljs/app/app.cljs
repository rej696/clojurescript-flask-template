(ns app.app
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

;; View ---------

; (def current-time (atom nil))


(defn get-time [endpoint current-time]
  (go (let [response (<! (http/get endpoint {:with-credentials? false}))]
        (prn (:status response))
        (prn (:time (:body response)))
        (reset! current-time (:time (:body response))))))

(defn component
  []
  (let [current-time (atom nil)]
    (get-time "http://localhost:5000/api/v1/time" current-time)
    (prn current-time)
    (fn []
      [:h1
       (str "The current time is " @current-time)])))
