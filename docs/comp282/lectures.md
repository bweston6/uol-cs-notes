---
title: COMP282 - Lectures
layout: article
aside:
 toc: true
sidebar:
 nav: comp282
---
{% for post in site.posts %}
{% if post.tags contains "Lectures" and post.tags contains "COMP282" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
