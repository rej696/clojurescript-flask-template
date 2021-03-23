(ns app.app
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

;; View ---------

(def current-time (atom nil))


(defn get-time [endpoint]
  (go (let [response (<! (http/get endpoint))]
        (reset! current-time (get (:body response) "time")))))

(defn component
  []
  (get-time "http://localhost:5000/api/v1/time")
  (.log js/console current-time)
  [:h1
   (str "The current time is " current-time)])
