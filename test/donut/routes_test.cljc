(ns donut.routes-test
  (:require
   #?@(:clj [[clojure.test :refer :all]]
       :cljs [[cljs.test :refer [deftest is] :include-macros true]])
   [donut.routes :as der]))

(deftest merge-route-opts-test
  (is (= [["/users" {:name :users, :foo :bar}]]
         (der/merge-route-opts
          [["/users" {:name :users} {:foo :bar}]]))))

(deftest routes-by-name-test
  (is (= {:users ["/users" {:name :users, :foo :bar}]}
         (der/routes-by-name
          [["/users" {:name :users, :foo :bar}]])))

  (let [routes [["/users" {:name :users, :foo :bar}]
                ["/posts" {:name :posts, :abc :xyz}]]]
    (is (= {:posts ["/posts" {:name :posts, :abc :xyz}]}
           (der/routes-by-name routes "posts")
           (der/routes-by-name routes #"posts")))))

(deftest simple-routes-test
  (is (= [["/users" :users]]
         (der/simple-routes
          [["/users" {:name :users, :foo :bar}]]))))
