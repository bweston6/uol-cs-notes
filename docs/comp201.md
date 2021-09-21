---
title: COMP201
layout: article
aside:
 toc: true
sidebar:
 nav: comp201
---
{% for post in site.posts %}
{% if post.tags contains "COMP201" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
