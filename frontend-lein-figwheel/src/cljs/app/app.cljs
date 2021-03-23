(ns app.app
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

;; View ---------

(def time-api "http://localhost:5000/api/v1/time")

(def current-time (atom nil))

(defn get-time [endpoint]
  (go (let [response (<! (http/get endpoint {:with-credentials? false}))]
        (prn (str "GET " time-api " status: "(:status response)))
        (prn (str "GET " time-api " response: " (:time (:body response))))
        (reset! current-time (:time (:body response))))))

(defn component
  []
  (get-time time-api)
  (fn []
    [:div
     [:h1
      (str "The current time is " @current-time)]
     [:div
      [:input.btn {:type "button" :value "Update Time!"
                   :on-click (fn [] (get-time time-api))}]]]))
