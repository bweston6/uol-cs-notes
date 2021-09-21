---
title: COMP208
layout: article
aside:
 toc: true
sidebar:
 nav: comp208
---
{% for post in site.posts %}
{% if post.tags contains "COMP208" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
