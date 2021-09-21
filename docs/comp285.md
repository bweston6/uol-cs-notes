---
title: COMP285
layout: article
aside:
 toc: true
sidebar:
 nav: comp285
---
{% for post in site.posts %}
{% if post.tags contains "COMP285" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
