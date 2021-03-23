(ns app.app
  (:require [reagent.core :as r]))


(defn title [text]
  [:h1.site__title
   [:span.site__title-text text]])

(defn app []
  [:div
   [title "Hello Clojurians!"]
   [title "POOP"]])

