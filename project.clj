(defproject redmine-diff "0.1.0"
  :description "Calculate diff between branches in tasks"
  :url "http://github.com/dizzy7/redmine-diff"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main redmine-diff.core
  :aot [redmine-diff.core])
