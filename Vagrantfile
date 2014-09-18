# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"
Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
   config.vm.box = "ubuntu12041-for-mongodb-tutorial"
   config.vm.box_url = "http://dl.dropbox.com/u/4031118/Vagrant/ubuntu-12.04.1-server-i686-virtual.box"
   config.vm.network :forwarded_port, guest: 27017, host: 27018
   config.vm.synced_folder "./mongotutorial", "/mongotutorial"
   config.vm.provider :virtualbox do |vb|
      vb.gui = false
   end

   # preperation
   config.vm.provision :shell, :inline => "apt-get update"

   # lets install mongo
   config.vm.provision :shell, :inline => "sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10"
   config.vm.provision :shell, :inline => "echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' | sudo tee /etc/apt/sources.list.d/mongodb.list"
   config.vm.provision :shell, :inline => "sudo apt-get update"
   config.vm.provision :shell, :inline => "sudo apt-get install -y mongodb-10gen"
   config.vm.provision :shell, :inline => "sudo service mongodb start"

   # lets install java
   config.vm.provision :chef_solo do |chef|
      chef.cookbooks_path = "cookbooks"
      chef.add_recipe "java"
   end

end
